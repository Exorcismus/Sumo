package com.traffic.sumo;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import it.polito.appeal.traci.Edge;
import it.polito.appeal.traci.Lane;
import it.polito.appeal.traci.Repository;
import it.polito.appeal.traci.SumoTraciConnection;
import it.polito.appeal.traci.TLState;
import it.polito.appeal.traci.TrafficLight;
import it.polito.appeal.traci.Vehicle;

public class TrafficCoordination {

	public static SumoTraciConnection conn;

	public static void main(String[] args) {

		conn = new SumoTraciConnection("D:/Masters/SUMO SIMULATOR/SUMO NETWORK/NETWORKS/Dave 0.22 net/default.sumo.cfg", 1000);

		System.setProperty("it.polito.appeal.traci.sumo_exe", "D:/Masters/SUMO SIMULATOR/sumo-winbin-0.22.0/sumo-0.22.0/bin/sumo-gui");
		try {

			conn.addOption("start", "1");
			conn.runServer();

			Thread connectionThread = new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {

							synchronized (conn) {

								conn.nextSimStep();

							}
							Thread.sleep(1);
						} catch (IllegalStateException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			});
			connectionThread.start();

			Repository<TrafficLight> tlsRepository = conn.getTrafficLightRepository();
			Repository<Lane> laneRepositor=	conn.getLaneRepository();
			Repository<Edge> edgeRepositor=	conn.getEdgeRepository();

			Map<String, TrafficLight> mtls = tlsRepository.getAll();
			Map<String, Lane> mlane = laneRepositor.getAll();
			Map<String, Edge> mEdge = edgeRepositor.getAll();

			Iterator avaiableTrafficLight = mtls.keySet().iterator();
			Iterator avaiableLane = mlane.keySet().iterator();
			Iterator avaiableEdge = mlane.keySet().iterator();

			
			// while (avaiableTrafficLight.hasNext()) {

			TrafficLight tl = mtls.get(avaiableTrafficLight.next());
			Thread sc = new Thread(new SignalController(tl));
			sc.start();

			// }

			Object notifier = new Object();

			// while (true) {
			// conn.nextSimStep();
			//
			// }

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}
