package com.code.demo.Zookeeper;

import java.io.IOException;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZkDemo implements Watcher {
    
    ZooKeeper zk;
    
    public static final String SERVER = "10.60.1.246:2181";
    public static final int SESSION_TIMEOUT = 3000;
    
    public ZkDemo() {
        try {
            zk = new ZooKeeper(SERVER, SESSION_TIMEOUT, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        
    }
    
    public void process(WatchedEvent event) {
        
    }

}
