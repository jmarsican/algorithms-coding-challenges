package com.javiermarsicano.algorithms

import org.junit.Assert.assertEquals
import org.junit.Test

/*
Have the function GasStation(strArr) take strArr which will be an array consisting of the following elements: N which
will be the number of gas stations in a circular route and each subsequent element will be the string g:c where g is the
amount of gas in gallons at that gas station and c be the amount of gallons of gas needed to get to the following gas station.

For example strArr may be: ["4","3:1","2:2","1:2","0:1"]. Your goal is to return the index of the starting gas station that
will allow you to travel around the whole route once, otherwise return the string Impossible. For the example above, there
are 4 gas stations, and your program should return the string 1 because starting at station 1 you receive 3 gallons of gas
and spend 1 getting to the next station. Then you have 2 gallons +2 more at the next station and you spend 2 so you have
2 gallons when you get to the 3rd station. You then have 3 but you spend 2 getting to the final station, and at the final
station you receive 0 gallons and you spend your final gallon getting to your starting point. Starting at any other gas
station would make getting around the route impossible, so the answer is 1. If there are multiple gas stations that are
possible to start at, return the smallest index (of the gas station). N will be >= 2.
Examples
* */
class GasStationsTests {
    @Test
    fun sampleTest() {
        val stationsData = arrayOf("4","3:1","2:2","1:2","0:1")

        val result = gasStation(stationsData)

        assertEquals("1", result)
    }

    @Test
    fun `no station allows reach all other stations`(){
        val stationsData = arrayOf("4","1:1","2:2","1:2","0:1")

        val result = gasStation(stationsData)

        assertEquals("impossible", result)
    }

    @Test
    fun `last station and circle back`() {
        val stationsData = arrayOf("4","0:1","2:2","1:2","3:1")

        val result = gasStation(stationsData)

        assertEquals("4", result)
    }
}

fun gasStation(strArr: Array<String>): String {
    // parse data and load stations info
    val count = Integer.parseInt(strArr[0])
    val stations = mutableListOf<Pair<Int,Int>>()
    for (index in 1 until strArr.size){
        val first = Integer.parseInt(strArr[index].substring(0,1))
        val second = Integer.parseInt(strArr[index].substring(2,3))
        stations.add(first to second)
    }

    for (start in stations.indices) {
        var pos = start
        var gas = stations[pos].first - stations[pos].second
        pos = (pos+1)%count
        while(gas >= 0 && pos != start) {
            gas += stations[pos].first //fuel car
            //travel and consume fuel
            gas -= stations[pos].second
            pos = (pos+1)%count
            if (pos == start && gas >= 0) return (pos+1).toString() //arrived to starting point
        }
    }
    return "impossible";
}
