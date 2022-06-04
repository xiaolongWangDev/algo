package algorithm;


import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;
import java.util.UUID;

public class ConsistentHash {

    public ConsistentHash() throws NoSuchAlgorithmException {
    }

    public static class Host {
        public final String id;

        public Host(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Host{" +
                    "id='" + id + '\'' +
                    '}';
        }
    }

    public static class Request {
        public final String ip;

        public Request(String ip) {
            this.ip = ip;
        }

        @Override
        public String toString() {
            return "Request{" +
                    "ip='" + ip + '\'' +
                    '}';
        }
    }

    private final MessageDigest md = MessageDigest.getInstance("MD5");

    private static final int N_V_NODES = 1000;
    private static final String SEPARATOR = "_VNODE_";
    private final TreeMap<Integer, Host> virtualNodesToHost = new TreeMap<>();

    public void addHost(Host newHost) {
        for (int i = 0; i < N_V_NODES; i++) {
            int newHash = hashVNode(newHost, i);
            /* when there's a collision, just overwrite the existing v node */
//            if (virtualNodesToHost.containsKey(newHash)) {
//                System.out.println(newHash);
//            }
            virtualNodesToHost.put(newHash, newHost);
        }
    }

    public void removeHost(Host host) {
        for (int i = 0; i < N_V_NODES; i++) {
            int hash = hashVNode(host, i);
            virtualNodesToHost.remove(hash);
        }
    }

    public Host dispatch(Request request) {
        Integer ceilingKey = virtualNodesToHost.ceilingKey(request.ip.hashCode());
        int vNode = ceilingKey == null ? virtualNodesToHost.firstKey() : ceilingKey;
//        System.out.println(vNode);
        return virtualNodesToHost.get(vNode);
    }

    private int hashVNode(Host host, int index) {
        // use MD5 to get more evenly distributed nodes
        byte[] digest = md.digest((host.id + SEPARATOR + index).getBytes(StandardCharsets.UTF_8));
        // MD5 create 128 bit output which is 16 bytes
        // we only needs to map to an Integer range. So taking the lower 4 bytes.
        return ByteBuffer.wrap(digest, 12, 4).getInt();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        ConsistentHash ch = new ConsistentHash();
        Host hostA = new Host("a");
        Host hostB = new Host("b");
        Host hostC = new Host("c");
        ch.addHost(hostA);
        ch.addHost(hostB);
        ch.addHost(hostC);
        int count = 0;
        for (int i = 0; i < 10000; i++) {
            if (ch.dispatch(new Request(UUID.randomUUID().toString())).id.equals("a")) {
                count++;
            }
        }
        System.out.println(count);
        ch.removeHost(hostB);
        count = 0;
        for (int i = 0; i < 10000; i++) {
            if (ch.dispatch(new Request(UUID.randomUUID().toString())).id.equals("a")) {
                count++;
            }
        }
        System.out.println(count);
    }


}
