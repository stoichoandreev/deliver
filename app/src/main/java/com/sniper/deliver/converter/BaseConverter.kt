package com.sniper.deliver.converter

interface BaseConverter<in R, out VM> {

    fun map(response: R) : VM

}
