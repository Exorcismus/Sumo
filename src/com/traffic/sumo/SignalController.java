package com.traffic.sumo;

import java.util.List;

import it.polito.appeal.traci.Lane;
import it.polito.appeal.traci.LaneListQuery;
import it.polito.appeal.traci.ReadControlledLinksQuery;
import it.polito.appeal.traci.TrafficLight;

public class SignalController implements Runnable {

	private TrafficLight tl;
	private LaneListQuery controlledLanes;
	private ReadControlledLinksQuery controlledLinks;

	public SignalController(TrafficLight tl) {
		this.tl = tl;
		controlledLanes = tl.queryReadControlledLanes();
		controlledLinks = tl.queryReadControlledLinks();
	}

	// TLState state = new TLState("rrrrrrrrr");
	// tl.changeLightsState(state);

	@Override
	public void run() {
		while (true) {
			try {
				synchronized (TrafficCoordination.conn) {

				}
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
