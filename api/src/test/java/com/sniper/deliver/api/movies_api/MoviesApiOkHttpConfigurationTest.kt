package com.sniper.deliver.api.movies_api

import org.junit.Assert
import org.junit.Test


class MoviesApiOkHttpConfigurationTest {

    private val tested = MoviesApiOkHttpConfiguration()

    @Test
    fun `test MoviesApiOkHttpConfiguration has correct connectTimeout`() {
        //when
        val result = tested.connectTimeout
        //test
        Assert.assertEquals(MoviesApiOkHttpConfiguration.CONNECTION_TIMEOUT, result)
    }

    @Test
    fun `test MoviesApiOkHttpConfiguration has correct readTimeout`() {
        //when
        val result = tested.readTimeout
        //test
        Assert.assertEquals(MoviesApiOkHttpConfiguration.READ_TIMEOUT, result)
    }

    @Test
    fun `test MoviesApiOkHttpConfiguration has correct writeTimeout`() {
        //when
        val result = tested.writeTimeout
        //test
        Assert.assertEquals(MoviesApiOkHttpConfiguration.WRITE_TIMEOUT, result)
    }

}
