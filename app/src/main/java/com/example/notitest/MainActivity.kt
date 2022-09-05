package com.example.notitest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button1 = findViewById<Button>(R.id.button1)
        val text1HelloWorld = findViewById<TextView>(R.id.textview1)
        val edittext = findViewById<EditText>(R.id.editText1)
        val edittextName = findViewById<EditText>(R.id.editText2)
        val name = "Testowe powiadomienie"
        this.title = getString(R.string.app_name)
        text1HelloWorld.visibility = View.INVISIBLE

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val descriptionText = getString(R.string.noti_desc)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel("jakischannelid", name, importance)
            mChannel.description = descriptionText

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)

            this.window.apply {
                //clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                //addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                //decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                statusBarColor = Color.TRANSPARENT
            }
        }
        //przycisk onclick
        button1.setOnClickListener{
            //var textUp = "Siema "+text1HelloWorld.text
            //powiadomienie
            if (edittext.text.toString() != "" && edittextName.text.toString() != ""){
                val builder1 = NotificationCompat.Builder(this, "jakischannelid")
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle(edittextName.text)
                    //.setContentText("textUp")
                    .setContentText(edittext.text)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                with(NotificationManagerCompat.from(this)){
                    notify(1, builder1.build())
                }
                Toast.makeText(applicationContext,getString(R.string.sent_noti),Toast.LENGTH_SHORT).show()
            }else{
                val builder1 = NotificationCompat.Builder(this, "jakischannelid")
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle(getString(R.string.default_title))
                    //.setContentText("textUp")
                    .setContentText(getString(R.string.default_text))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                with(NotificationManagerCompat.from(this)){
                    notify(2, builder1.build())
                }
                Toast.makeText(applicationContext,getString(R.string.sent_noti),Toast.LENGTH_SHORT).show()
            }
        }
    }
}