package com.example.backend;


import org.hipparchus.util.FastMath;
import org.orekit.data.DataContext;
import org.orekit.data.DataProvider;
import org.orekit.data.DirectoryCrawler;
import org.orekit.frames.Frame;
import org.orekit.frames.FramesFactory;
import org.orekit.orbits.KeplerianOrbit;
import org.orekit.orbits.Orbit;
import org.orekit.orbits.PositionAngleType;
import org.orekit.propagation.Propagator;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.analytical.KeplerianPropagator;
import org.orekit.propagation.analytical.tle.TLE;
import org.orekit.propagation.analytical.tle.TLEPropagator;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.TimeScalesFactory;
import org.orekit.utils.Constants;
import org.orekit.utils.PVCoordinates;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Orbitas {
    TLEData d;

    final File orekitData = new File("/Users/nilszelder/Documents/GitHub/orekit-data-main");
    final DataProvider dirCrawler = new DirectoryCrawler(orekitData);

    List<SpacecraftState> l = new ArrayList<>();

    public Orbitas(TLEData d){
        this.d = d;
    }

    public List<SpacecraftState> propagate(){
        DataContext.getDefault().getDataProvidersManager().addProvider(dirCrawler);

        //double sma = 7000e3; // Semi-major axis [m]
        //double ecc = 0.001; // Eccentricity [-]
        //double inc = FastMath.toRadians(15); // Inclination [rad]
        //double pa = FastMath.toRadians(30); // Perigee Argument [rad]
        //double raan = FastMath.toRadians(45); // Right Ascension of the Ascending Node[rad]
        //double anomaly = FastMath.toRadians(60); // Inclination [rad]

        PositionAngleType positionAngleType = PositionAngleType.MEAN; // Type of anomaly angle used (MEAN, TRUE, ECCENTRIC)
        Frame inertialFrame = FramesFactory.getGCRF(); // Earth-Centered Inertial frame
        //AbsoluteDate date = new AbsoluteDate(2002, 1, 1, 0, 0, 0, TimeScalesFactory.getUTC()); // Date of the orbit
        //double mu = Constants.EIGEN5C_EARTH_MU; // Earth's standard gravitational parameter used in EIGEN-5C gravity field model

        //Orbit orbit = new KeplerianOrbit(sma, ecc, inc, pa, raan, anomaly,
                //positionAngleType, inertialFrame, date, mu);

        //Propagator propagator = new KeplerianPropagator(orbit);

        TLE tle = new TLE(d.line1, d.line2);
        TLEPropagator propagator = TLEPropagator.selectExtrapolator(tle);

        AbsoluteDate date = tle.getDate();

        SpacecraftState state = propagator.propagate(date);

        //PVCoordinates pvCoordinates = propagator.getPVCoordinates(date, FramesFactory.getTEME());
        //Orbit orbit = new KeplerianOrbit(pvCoordinates, FramesFactory.getTEME(), date, Constants.EGM96_EARTH_MU);

        PVCoordinates pvCoordinates = state.getPVCoordinates();
        Frame frame = FramesFactory.getTEME(); // TLEs sind in TEME definiert
        double mu = Constants.EGM96_EARTH_MU;

        KeplerianOrbit orbit = new KeplerianOrbit(pvCoordinates, frame, date, mu);

        // Daten ausgeben
        double sma = orbit.getA();
        double ecc = orbit.getE();
        double inc = orbit.getI();
        double pa = orbit.getPerigeeArgument();
        double raan = orbit.getRightAscensionOfAscendingNode();
        double anomaly = orbit.getTrueAnomaly();

        /*System.out.println("sma: " + orbit.getA());
        System.out.println("ecc: " + orbit.getE());
        System.out.println("inc: " + orbit.getI());
        System.out.println("pa: " + orbit.getPerigeeArgument());
        System.out.println("raan: " + orbit.getRightAscensionOfAscendingNode());
        System.out.println("anomaly: " + orbit.getTrueAnomaly());*/

        double timeSteps = 120;

        for (int i = 0; i < 5; i++){
            AbsoluteDate targetDate = date.shiftedBy(i * timeSteps);
            SpacecraftState propagatedState = propagator.propagate(targetDate);
            l.add(propagatedState);


        }

        //AbsoluteDate targetDate = date.shiftedBy(Constants.JULIAN_DAY);
        //SpacecraftState propagatedState = propagator.propagate(targetDate);

        return l;


    }




}
