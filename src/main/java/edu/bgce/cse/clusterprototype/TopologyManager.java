package edu.bgce.cse.clusterprototype;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopologyManager {

    private static Logger logger = LoggerFactory.getLogger(TopologyManager.class);

    public static void main(String[] args) throws Exception {


        logger.info("Starting Topology Manager...");

        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("word", new FilesystemSpout(), 10);
        builder.setBolt("exclaim1", new ExclamationBolt(), 3).shuffleGrouping("word");
        builder.setBolt("exclaim2", new ExclamationBolt(), 2).shuffleGrouping("exclaim1");
        builder.setBolt("exclaim3", new ExclamationBolt(), 2).shuffleGrouping("exclaim1");

        Config conf = new Config();
        conf.setDebug(true);

        if (args != null && args.length > 0) {
            logger.info("Running inside a real cluster.");
            conf.setNumWorkers(3);

            StormSubmitter.submitTopologyWithProgressBar(args[0], conf, builder.createTopology());
        }
        else {
            logger.info("Running as LocalCluster");

            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("test", conf, builder.createTopology());
//            Utils.sleep(10000);
            while (true) {
            }
//            cluster.killTopology("test");
//            cluster.shutdown();
        }
    }
}
