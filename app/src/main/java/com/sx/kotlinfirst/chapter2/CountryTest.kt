package com.sx.kotlinfirst.chapter2

/**
 * @author sunxin
 * @date 2019-12-10 16:56
 * @desc
 */
class CountryTest {


    /**
     * 是否是一个欧洲大国
     */
    fun isBigEuCountry(country: Country): Boolean {
        return country.continent == "EU" && country.population > 10000
    }


    /**
     * 过滤国家
     */
    fun filterCountries(countries: List<Country>, isBigCountry:(Country)-> Boolean): List<Country> {
        val res = mutableListOf<Country>()
        for (country in countries) {

            if (isBigCountry(country)) {
                res.add(country)
            }
        }
        return res
    }


}