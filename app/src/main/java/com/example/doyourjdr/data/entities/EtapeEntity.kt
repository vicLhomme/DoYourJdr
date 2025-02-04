package com.example.doyourjdr.data.entities

class EtapeEntity (
    val libelle : String,
    val numero : Int,
    val personnages : MutableList<PersonnageEntity>,
    val cartes : MutableList<CartesEntity>,
    val notesAvancement : String,
    val bilan : MutableList<AmeliorationEntity>,
    val coordonneePosition : Pair<Long, Long>
)
