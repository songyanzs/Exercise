package com.syzs.storm;


import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;

public class NumberTopology {

    public static void main(String[] args) {

        //创建任务的拓扑图
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        //设置拓扑关系（Spout）
        topologyBuilder.setSpout("numberSpout",new NumberSpout());
        //设置拓扑关系(Bolt )
        topologyBuilder.setBolt("number1",new NumberBolt()).shuffleGrouping("numberSpout");

        //启动Togology
        Config conf = new Config();
        //创建一个togology
        StormTopology topology = topologyBuilder.createTopology();
        //本地模式启动集群
        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("numberTopology",conf,topology);

    }
}
