package com.example.backend;


import org.orekit.propagation.SpacecraftState;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class Controller {
    @PostMapping("/satellite")
    public List<String> receiveSatelliteData(@RequestBody String data){
        //System.out.println(data);
        //System.out.println("\\r?\\n");
        TLEData d = new TLEData(data);
        //System.out.println(d.getLine1());

        //System.out.println(data);
        Orbitas orbitas = new Orbitas(d);
        //ReturnData data1 = new ReturnData();
        //data1.setData(orbitas.propagate().toString());


        List<SpacecraftState> propStates = orbitas.propagate();
        List<String> stateStrings = new ArrayList<>();
        for (SpacecraftState s: propStates){
            stateStrings.add(s.toString());
        }


        return stateStrings;
        /*return propStates.stream().map(state -> {
            SatRet doo = new SatRet();
            doo.setDate(state.getDate().toString());
            doo.setPosition(state.getPVCoordinates().getPosition().toString());
            doo.setVelocity(state.getPVCoordinates().getVelocity().toString());
            return doo;
        }).toList();*/


    }
}
