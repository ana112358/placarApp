package com.example.placarapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.placarapp.R
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


class MainActivity : ComponentActivity() {

    // Variáveis para armazenar a pontuação de cada time
    private var pontuacaoTimeA: Int = 0
    private var pontuacaoTimeB: Int = 0

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
        bTresPontosTimeA.setOnClickListener{adicionarPontos(3,"A")}
        bDoisPontosTimeA.setOnClickListener { adicionarPontos(2, "A") }
        bTLivreTimeA.setOnClickListener { adicionarPontos(1, "A") }

        bTresPontosTimeB.setOnClickListener { adicionarPontos(3, "B") }
        bDoisPontosTimeB.setOnClickListener { adicionarPontos(2, "B") }
        bTLivreTimeB.setOnClickListener { adicionarPontos(1, "B") }

        bReiniciar.setOnClickListener { reiniciarPartida() }

    }

    fun adicionarPontos(pontos: Int, time: String) {
        if(time=="A"){
            pontuacaoTimeA+=pontos
        }else{
            pontuacaoTimeB+=pontos
        }
        atualizaPlacar(time)
    }
    fun atualizaPlacar(time: String){
        if(time=="A"){
            pTimeA.setText(pontuacaoTimeA.toString())
        }else{
            pTimeB.setText(pontuacaoTimeB.toString())
        }
    }

    fun reiniciarPartida(){
        pontuacaoTimeA=0
        pontuacaoTimeB=0

        // Atualiza os TextViews com os valores zerados
        pTimeA.setText(pontuacaoTimeA.toString())
        pTimeB.setText(pontuacaoTimeB.toString())

        Toast.makeText(this,"Placar reiniciado",Toast.LENGTH_SHORT).show()
    }
}