package com.team.voided.lib

class Vec2i(var x: Int, var y: Int) {
    fun add(toAdd: Vec2i){
        this.x += toAdd.x
        this.y+= toAdd.y
    }
    fun copy():Vec2i{
        return Vec2i(this.x, this.y)
    }
}