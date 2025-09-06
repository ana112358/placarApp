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

    private lateinit var faltasA: TextView
    private lateinit var faltasB: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main)

        // INICIALIZAÇÃO DOS TEXTVIEWS
        pTimeA = findViewById(R.id.placarTimeA)
        pTimeB = findViewById(R.id.placarTimeB)
        faltasA = findViewById(R.id.faltasTimeA)
        faltasB = findViewById(R.id.faltasTimeB)

        // INICIALIZAÇÃO DOS BOTÕES
        val bTresPontosTimeA: Button = findViewById(R.id.tresPontosA)
        val bDoisPontosTimeA: Button = findViewById(R.id.doisPontosA)
        val bTLivreTimeA: Button = findViewById(R.id.tiroLivreA)
        val bFaltaTimeA: Button = findViewById(R.id.faltaTimeA)

        val bTresPontosTimeB: Button = findViewById(R.id.tresPontosB)
        val bDoisPontosTimeB: Button = findViewById(R.id.doisPontosB)
        val bTLivreTimeB: Button = findViewById(R.id.tiroLivreB)
        val bFaltaTimeB: Button = findViewById(R.id.faltaTimeB)

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
        bFaltaTimeA.setOnClickListener {
            adicionarFalta("A")
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
        bFaltaTimeB.setOnClickListener {
            adicionarFalta("B")
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

    fun adicionarFalta(time: String) {
        if (time == "A") {
            faltasTimeA++
            if (faltasTimeA >= 5) {
                Toast.makeText(this, "⚠️ Time A: Limite de faltas atingido!", Toast.LENGTH_LONG).show()
            }
        } else {
            faltasTimeB++
            if (faltasTimeB >= 5) {
                Toast.makeText(this, "⚠️ Time B: Limite de faltas atingido!", Toast.LENGTH_LONG).show()
            }
        }

        atualizarTodosDisplays()
    }

    fun atualizarTodosDisplays() {
        pTimeA.text = pontuacaoTimeA.toString()
        pTimeB.text = pontuacaoTimeB.toString()
        faltasA.text = "Faltas: $faltasTimeA"
        faltasB.text = "Faltas: $faltasTimeB"

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
        faltasTimeA = 0
        faltasTimeB = 0

        atualizarTodosDisplays()

        Toast.makeText(this, "Placar reiniciado!", Toast.LENGTH_SHORT).show()
    }
}