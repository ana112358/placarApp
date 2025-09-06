package com.example.placarapp
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import android.widget.ScrollView
import android.widget.LinearLayout


class MainActivity : ComponentActivity() {

    private var pontuacaoTimeA: Int = 0
    private var pontuacaoTimeB: Int = 0

    // Variáveis para faltas
    private var faltasTimeA: Int = 0
    private var faltasTimeB: Int = 0

    private lateinit var pTimeA: TextView
    private lateinit var pTimeB: TextView

    private lateinit var faltasA: TextView
    private lateinit var faltasB: TextView
    private lateinit var containerHistorico: LinearLayout
    private lateinit var scrollHistorico: ScrollView


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
        containerHistorico = findViewById(R.id.containerHistorico)
        scrollHistorico = findViewById(R.id.scrollHistorico)

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
            adicionarEvento("🏀 Time A marcou $pontos ponto(s)")

        } else {
            pontuacaoTimeB += pontos
            adicionarEvento("🏀 Time B marcou $pontos ponto(s)")
        }

        atualizarTodosDisplays()
    }

    fun adicionarFalta(time: String) {
        if (time == "A") {
            faltasTimeA++
            adicionarEvento("⚠️ Falta cometida pelo Time A")
            if (faltasTimeA >= 5) {
                Toast.makeText(this, "⚠️ Time A: Limite de faltas atingido!", Toast.LENGTH_LONG).show()
            }
        } else {
            faltasTimeB++
            adicionarEvento("⚠️ Falta cometida pelo Time B")
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

        containerHistorico.removeAllViews() // limpa histórico
        adicionarEvento("🔄 Partida reiniciada")

        atualizarTodosDisplays()
        Toast.makeText(this, "Placar reiniciado!", Toast.LENGTH_SHORT).show()
    }
    private fun adicionarEvento(texto: String) {
        val novoEvento = TextView(this)
        novoEvento.text = texto
        novoEvento.setTextColor(resources.getColor(android.R.color.white))
        novoEvento.textSize = 14f
        novoEvento.setPadding(4, 4, 4, 4)

        containerHistorico.addView(novoEvento)

        scrollHistorico.post {
            scrollHistorico.fullScroll(ScrollView.FOCUS_DOWN)
        }
    }

}

