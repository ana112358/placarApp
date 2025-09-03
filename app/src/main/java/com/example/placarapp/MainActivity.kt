package com.example.placarapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {

    private var pontuacaoTimeA: Int = 0
    private var pontuacaoTimeB: Int = 0
    private lateinit var bTresPontosA: Button
    private lateinit var bDoisPontosA: Button
    private lateinit var bTiroLivreA: Button
    private lateinit var bTresPontosB: Button
    private lateinit var bDoisPontosB: Button
    private lateinit var bTiroLivreB: Button
    private lateinit var bReiniciar: Button
    private lateinit var pTimeA: TextView
    private lateinit var pTimeB: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_main)

        pTimeA = findViewById(R.id.placarTimeA)
        pTimeB = findViewById(R.id.placarTimeB)
        bTresPontosA = findViewById(R.id.tresPontosA)
        bDoisPontosA = findViewById(R.id.doisPontosA)
        bTiroLivreA = findViewById(R.id.tiroLivreA)

        bTresPontosB = findViewById(R.id.tresPontosB)
        bDoisPontosB = findViewById(R.id.doisPontosB)
        bTiroLivreB = findViewById(R.id.tiroLivreB)

        bReiniciar = findViewById(R.id.reiniciarPartida)
        bTresPontosA.setOnClickListener { adicionarPontos(3, "A") }
        bDoisPontosA.setOnClickListener { adicionarPontos(2, "A") }
        bTiroLivreA.setOnClickListener { adicionarPontos(1, "A") }

        bTresPontosB.setOnClickListener { adicionarPontos(3, "B") }
        bDoisPontosB.setOnClickListener { adicionarPontos(2, "B") }
        bTiroLivreB.setOnClickListener { adicionarPontos(1, "B") }

        bReiniciar.setOnClickListener { reiniciarPartida() }
    }



    fun adicionarPontos(pontos: Int, time: String) {
        if(time == "A") {
            pontuacaoTimeA += pontos
        } else {
            pontuacaoTimeB += pontos
        }
        atualizarPlacar(time)

    }

    fun atualizarPlacar(time: String){
        if (time == "A") {
            pTimeA.text = pontuacaoTimeA.toString()
        } else {
            pTimeB.text = pontuacaoTimeB.toString()
        }
    }


    fun reiniciarPartida() {
        pontuacaoTimeA = 0
        pTimeA.setText(pontuacaoTimeA.toString())
        pontuacaoTimeB = 0
        pTimeB.setText(pontuacaoTimeB.toString())
        Toast.makeText(this,"Placar reiniciado",Toast.LENGTH_SHORT).show()
    }



}



