package com.example.placarapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import android.media.MediaPlayer
import android.view.animation.AnimationUtils
import android.app.AlertDialog


class MainActivity : ComponentActivity() {

    // Variáveis para armazenar a pontuação de cada time
    private var pontuacaoTimeA: Int = 0
    private var pontuacaoTimeB: Int = 0

    // Variáveis para faltas
    private var faltasTimeA: Int = 0
    private var faltasTimeB: Int = 0

    private lateinit var pTimeA: TextView
    private lateinit var pTimeB: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main)

        // INICIALIZAÇÃO DOS TEXTVIEWS
        pTimeA = findViewById(R.id.placarTimeA)
        pTimeB = findViewById(R.id.placarTimeB)

        // INICIALIZAÇÃO DOS BOTÕES
        val bTresPontosTimeA: Button = findViewById(R.id.tresPontosA)
        val bDoisPontosTimeA: Button = findViewById(R.id.doisPontosA)
        val bTLivreTimeA: Button = findViewById(R.id.tiroLivreA)

        val bTresPontosTimeB: Button = findViewById(R.id.tresPontosB)
        val bDoisPontosTimeB: Button = findViewById(R.id.doisPontosB)
        val bTLivreTimeB: Button = findViewById(R.id.tiroLivreB)

        val bReiniciar: Button = findViewById(R.id.reiniciarPartida)

        // CONFIGURAÇÃO DOS LISTENERS
        bTresPontosTimeA.setOnClickListener {
            adicionarPontos(3, "A")
        }
        bDoisPontosTimeA.setOnClickListener {
            adicionarPontos(2, "A")
        }
        bTLivreTimeA.setOnClickListener {
            adicionarPontos(1, "A")
        }

        bTresPontosTimeB.setOnClickListener {
            adicionarPontos(3, "B")
        }
        bDoisPontosTimeB.setOnClickListener {
            adicionarPontos(2, "B")
        }
        bTLivreTimeB.setOnClickListener {
            adicionarPontos(1, "B")
        }

        bReiniciar.setOnClickListener { reiniciarPartida() }

        // Inicializar displays
        atualizarTodosDisplays()
    }

    fun adicionarPontos(pontos: Int, time: String) {
        if (time == "A") {
            pontuacaoTimeA += pontos
        } else {
            pontuacaoTimeB += pontos
        }

        atualizarTodosDisplays()
    }

    fun atualizarTodosDisplays() {
        pTimeA.text = pontuacaoTimeA.toString()
        pTimeB.text = pontuacaoTimeB.toString()

        // Destacar time que está ganhando
        if (pontuacaoTimeA > pontuacaoTimeB) {
            pTimeA.setTextColor(resources.getColor(android.R.color.holo_green_dark))
            pTimeB.setTextColor(resources.getColor(android.R.color.white))
        } else if (pontuacaoTimeB > pontuacaoTimeA) {
            pTimeB.setTextColor(resources.getColor(android.R.color.holo_green_dark))
            pTimeA.setTextColor(resources.getColor(android.R.color.white))
        } else {
            pTimeA.setTextColor(resources.getColor(android.R.color.white))
            pTimeB.setTextColor(resources.getColor(android.R.color.white))
        }
    }

    fun reiniciarPartida() {
        pontuacaoTimeA = 0
        pontuacaoTimeB = 0

        atualizarTodosDisplays()

        Toast.makeText(this, "Placar reiniciado!", Toast.LENGTH_SHORT).show()
    }
}