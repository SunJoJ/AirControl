package com.example.aircontrol.models

object Cities {

    data class CityWithCode(val cityName: String, val isoCode: String)

    val citiesArray = arrayOf(CityWithCode("beijing", "cn"), CityWithCode("moscow", "ru"),
        CityWithCode("wroclaw","pl"), CityWithCode("warsaw","pl"),
        CityWithCode("berlin", "de"), CityWithCode("poznan", "pl"))

    val allCitiesArray = arrayOf(CityWithCode("beijing", "cn"), CityWithCode("moscow", "ru"),
        CityWithCode("wroclaw","pl"), CityWithCode("warsaw","pl"),
        CityWithCode("berlin", "de"), CityWithCode("poznan", "pl"), CityWithCode("shanghai","cn"), CityWithCode("kaliningrad","ru"),
        CityWithCode("leningrad","ru"), CityWithCode("macao","mo"), CityWithCode("kyoto","jp"), CityWithCode("ufa","ru"),
        CityWithCode("toronto","ca"), CityWithCode("bishkek","kg"), CityWithCode("montreal","ca"), CityWithCode("lima","pe"),
        CityWithCode("paris","fr"), CityWithCode("zurich","ch"), CityWithCode("barcelona","es"),
        CityWithCode("prague","cz"), CityWithCode("milan","it"), CityWithCode("ostrava","ca"), CityWithCode("katowice","pl"),
        CityWithCode("gdansk","pl"), CityWithCode("szczecin","pl"), CityWithCode("lublin","pl"),
        CityWithCode("amsterdam","nl"), CityWithCode("vilnius","lt"), CityWithCode("hamburg", "de"),
        CityWithCode("stockholm","se"), CityWithCode("malmo", "se"), CityWithCode("kiev","ua"), CityWithCode("ankara","tr"),
        CityWithCode("dnipro","ua"), CityWithCode("odesa","ua"), CityWithCode("copenhagen","dk"), CityWithCode("eindhoven", "nl"),
        CityWithCode("namur","be"), CityWithCode("leuven","be"), CityWithCode("lodz","pl"), CityWithCode("kaunas","lt"),
        CityWithCode("Florence","it"), CityWithCode("rome","it"), CityWithCode("brno","cz"),CityWithCode( "london","gb"),
        CityWithCode("lisbon", "pt"), CityWithCode("tetovo", "mk"), CityWithCode("cair","eg"), CityWithCode("zenica", "ba"),
        CityWithCode("skopje", "mk"), CityWithCode("veles", "mk"), CityWithCode("cluj-napoka","ro"),
        CityWithCode("bern","ch"), CityWithCode("cuenca","es"), CityWithCode("lagun", "ph"), CityWithCode("san-juan","tt"),
        CityWithCode("krasnoyarsk", "ru"), CityWithCode( "dhaka","bd"), CityWithCode( "chelyabinsk", "ru"), CityWithCode("bangkok","th"),
        CityWithCode("chicago","us"), CityWithCode("portland","us"),CityWithCode( "melbourne","ca"), CityWithCode("syd","au"),
        CityWithCode("mexico", "mx"))
}