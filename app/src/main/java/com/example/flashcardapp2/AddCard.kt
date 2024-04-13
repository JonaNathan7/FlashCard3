// Importation des bibliothèques nécessaires
package com.example.flashcardapp2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

// Définition de la classe AddCard qui hérite de AppCompatActivity
class AddCard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)

        // Récupération des références aux EditText et aux ImageView
        val questionEditText = findViewById<EditText>(R.id.editTextField)
        val answerEditText = findViewById<EditText>(R.id.editTextField1)
        val wrongAnswer1EditText = findViewById<EditText>(R.id.editTextField2)
        val wrongAnswer2EditText = findViewById<EditText>(R.id.editTextField3)
        val closeButton = findViewById<ImageView>(R.id.icone_X)
        val saveButton = findViewById<ImageView>(R.id.icone_save)

        // Gestion du clic sur le bouton de fermeture
        closeButton.setOnClickListener {
            // Retour à l'activité MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Récupération des données passées depuis MainActivity
        val question = intent.getStringExtra("question")
        val answer = intent.getStringExtra("answer")
        val wrongAnswer1 = intent.getStringExtra("wrongAnswer1")
        val wrongAnswer2 = intent.getStringExtra("wrongAnswer2")

        // Mise à jour des EditText avec les données existantes
        questionEditText.setText(question)
        answerEditText.setText(answer)
        wrongAnswer1EditText.setText(wrongAnswer1)
        wrongAnswer2EditText.setText(wrongAnswer2)

        // Gestion du clic sur le bouton de sauvegarde
        saveButton.setOnClickListener {
            // Récupération des valeurs des EditText
            val question = questionEditText.text.toString()
            val answer = answerEditText.text.toString()
            val wrongAnswer1 = wrongAnswer1EditText.text.toString()
            val wrongAnswer2 = wrongAnswer2EditText.text.toString()

            // Vérification si tous les champs sont remplis
            if (question.isBlank() || answer.isBlank() || wrongAnswer1.isBlank() || wrongAnswer2.isBlank()) {
                // Affichage d'un message d'erreur avec Snackbar si l'un des champs est vide
                Snackbar.make(findViewById(R.id.icone_save), "Veuillez remplir tous les champs", Snackbar.LENGTH_SHORT).show()
            } else {
                // Les champs sont remplis, continuer avec la sauvegarde des données
                Snackbar.make(findViewById(R.id.icone_save), "Carte créée avec succès", Snackbar.LENGTH_SHORT).show()
                val data = Intent()
                // Passage des données à l'activité MainActivity
                data.putExtra("question", question)
                data.putExtra("answer", answer)
                data.putExtra("wrongAnswer1", wrongAnswer1)
                data.putExtra("wrongAnswer2", wrongAnswer2)
                setResult(Activity.RESULT_OK, data)
                // Fermeture de l'activité AddCard
                finish()
            }
        }
    }
}
