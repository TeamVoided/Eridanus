package com.team.voided.dung

class UnknownTile(var pStates: Array<Tile>) {

    companion object {
        //        val EMPTY = UnknownTile(emptyArray())
        val FULL = UnknownTile(arrayOf(Tile.IRON, Tile.NETHER, Tile.DEEP, Tile.COPPER, Tile.NOTHING))
    }
    private constructor(pStates: Array<Tile>, coll: Boolean) : this(pStates){
        this.collapsed = coll
    }

    private var collapsed: Boolean = false

    fun isCollapsed(): Boolean {
        return this.collapsed
    }

    fun collapse() {
        this.collapsed = true
    }

    fun entropyLevel(): Int {
        return this.pStates.size
    }
    fun copy(): UnknownTile{
        return UnknownTile(this.pStates, this.collapsed)
    }


}