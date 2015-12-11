package com.lambert.hrpc;

import com.lambert.hrpc.registry.ServiceRegistry;
import com.lambert.hrpc.registry.zookeeper.ZookeeperServiceRegistry;
import com.lambert.hrpc.serialization.Serializer;
import com.lambert.hrpc.serialization.protostuff.ProtostuffSerializer;
import com.lambert.hrpc.transportation.ReceiveServer;
import com.lambert.hrpc.transportation.netty.NettyReceiveServer;

/**
 * RPC 运行上下文
 * Created by pc on 2015/12/11.
 */
public class RpcContext {

    private Serializer serializer;
    private ServiceRegistry serviceRegistry;
    private ReceiveServer receiveServer;

    private RpcConf conf ;


    public void initComponent(){
        // init default component

        if(conf == null) {
            conf = new RpcConf();
        }

        this.serializer = new ProtostuffSerializer();

        this.serviceRegistry = new ZookeeperServiceRegistry(conf);

        this.receiveServer = new NettyReceiveServer(this);
    }

    public RpcContext(){
        initComponent();
    }

    public Serializer getSerializer() {
        return serializer;
    }

    public void setSerializer(Serializer serializer) {
        this.serializer = serializer;
    }

    public ServiceRegistry getServiceRegistry() {
        return serviceRegistry;
    }

    public void setServiceRegistry(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    public ReceiveServer getReceiveServer() {
        return receiveServer;
    }

    public void setReceiveServer(ReceiveServer receiveServer) {
        this.receiveServer = receiveServer;
        this.receiveServer.setContext(this);
    }

    public RpcConf getConf() {
        return conf;
    }

    public void setConf(RpcConf conf) {
        this.conf = conf;
    }
}
