package com.sniper.deliver.api.movies_api

import org.junit.Assert.assertEquals
import org.junit.Test

class MoviesApiConfigurationTest{

    @Test
    fun `test MoviesApiConfiguration has correct base URL for test builds`() {
        //given
        val tested = MoviesApiConfiguration(MoviesApiEnvironment,true)
        //when
        val result = tested.baseURL
        //test
        assertEquals(MoviesApiHelper.API_QA_URL, result)
    }

    @Test
    fun `test MoviesApiConfiguration has correct base URL for production builds`() {
        //given
        val tested = MoviesApiConfiguration(MoviesApiEnvironment, false)
        //when
        val result = tested.baseURL
        //test
        assertEquals(MoviesApiHelper.API_PRODUCTION_URL, result)
    }

}
