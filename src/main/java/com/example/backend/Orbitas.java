package com.example.backend;

import org.hipparchus.util.FastMath;
import org.orekit.data.DataContext;
import org.orekit.data.DataProvider;
import org.orekit.data.DirectoryCrawler;
import org.orekit.frames.Frame;
import org.orekit.orbits.Orbit;
import org.orekit.frames.FramesFactory;
import org.orekit.orbits.KeplerianOrbit;
import org.orekit.orbits.PositionAngleType;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.TimeScalesFactory;
import org.orekit.utils.Constants;

import java.awt.*;
import java.io.File;

public class Orbit {

    final File orekitData = new File("/Users/nilszelder/Documents/GitHub/orekit-data-main");
    final DataProvider dirCrawler = new DirectoryCrawler(orekitData);
    DataContext.getDefault().getDataProvidersManager().addProvider(dirCrawler);

    double sma = 7000e3; // Semi-major axis [m]
    double ecc = 0.001; // Eccentricity [-]
    double inc = FastMath.toRadians(15); // Inclination [rad]
    double pa = FastMath.toRadians(30); // Perigee Argument [rad]
    double raan = FastMath.toRadians(45); // Right Ascension of the Ascending Node[rad]
    double anomaly = FastMath.toRadians(60); // Inclination [rad]

    PositionAngleType positionAngleType = PositionAngleType.MEAN; // Type of anomaly angle used (MEAN, TRUE, ECCENTRIC)
    Frame inertialFrame = FramesFactory.getGCRF(); // Earth-Centered Inertial frame
    AbsoluteDate date = new AbsoluteDate(2002, 1, 1, 0, 0, 0, TimeScalesFactory.getUTC()); // Date of the orbit
    double mu = Constants.EIGEN5C_EARTH_MU; // Earth's standard gravitational parameter used in EIGEN-5C gravity field model

    Orbit orbit = new KeplerianOrbit(sma, ecc, inc, pa, raan, anomaly,
            positionAngleType, inertialFrame, date, mu);
}
