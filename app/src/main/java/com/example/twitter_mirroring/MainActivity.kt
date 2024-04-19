package com.example.twitter_mirroring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.twitter_mirroring.databinding.ActivityMainBinding
import com.example.twitter_mirroring.model.TweetData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.Calendar

class MainActivity : AppCompatActivity() {

     //Implementing ViewBinding
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setting up the navigation for the app
        configNav()

        val jsonData = JSONArray("[\n" +
                "{\n" +
                "'cantBookmarks': 0,\n" +
                "'cantLikes': 25,\n" +
                "'cantReplies': 0,\n" +
                "'cantRetweetsAndReposts': 2,\n" +
                "'cantViews': 6371,\n" +
                "'device': 'iOS',\n" +
                "'hasMedia': 'false',\n" +
                "'hourAndDate': 1713376860,\n" +
                "'mediaCount': 0,\n" +
                "'photoUrl1': '',\n" +
                "'photoUrl2': '',\n" +
                "'photoUrl3': '',\n" +
                "'photoUrl4': '',\n" +
                "'profilePhoto': 'https://pbs.twimg.com/profile_images/1770770254198640641/MW4GrVo8_normal.jpg',\n" +
                "'publicName': 'Brais Moure',\n" +
                "'realUsername': 'MoureDev',\n" +
                "'tweetContent': 'Estoy en directo!\n\nHoy tenemos charla sobre programación y desarrollo.\nHablamos de la nueva edición de mi libro, las charlas seleccionadas y la hackathon del Hola Mundo day.\n\n\uD83D\uDFE3 Estoy en Twitch',\n" +
                "'verifiedLogo': 'true'\n" +
                "},\n" +
                "{\n" +
                "'cantBookmarks': 2,\n" +
                "'cantLikes': 188,\n" +
                "'cantReplies': 30,\n" +
                "'cantRetweetsAndReposts': 3,\n" +
                "'cantViews': 20000,\n" +
                "'device': 'Android',\n" +
                "'hasMedia': 'true',\n" +
                "'hourAndDate': 1713193320,\n" +
                "'mediaCount': 1,\n" +
                "'photoUrl1': 'https://pbs.twimg.com/media/GLNutzsXMAAkPB3?format=jpg&name=900x900',\n" +
                "'photoUrl2': '',\n" +
                "'photoUrl3': '',\n" +
                "'photoUrl4': '',\n" +
                "'profilePhoto': 'https://pbs.twimg.com/profile_images/1770770254198640641/MW4GrVo8_normal.jpg',\n" +
                "'publicName': 'Brais Moure',\n" +
                "'realUsername': 'MoureDev',\n" +
                "'tweetContent': 'Compré esta salsa de ají chileno sólo por su UI',\n" +
                "'verifiedLogo': 'true'\n" +
                "},\n" +
                "{\n" +
                "'cantBookmarks': 1100,\n" +
                "'cantLikes': 1600,\n" +
                "'cantReplies': 8,\n" +
                "'cantRetweetsAndReposts': 429,\n" +
                "'cantViews': 60800,\n" +
                "'device': 'iOS',\n" +
                "'hasMedia': 'true',\n" +
                "'hourAndDate': 1713549660,\n" +
                "'mediaCount': 2,\n" +
                "'photoUrl1': 'https://pbs.twimg.com/media/GKFTbU8WIAEVnl1?format=jpg&name=medium',\n" +
                "'photoUrl2': 'https://pbs.twimg.com/media/GKFTbl3WcAIT4jC?format=jpg&name=large',\n" +
                "'photoUrl3': '',\n" +
                "'photoUrl4': '',\n" +
                "'profilePhoto': 'https://pbs.twimg.com/profile_images/1770770254198640641/MW4GrVo8_normal.jpg',\n" +
                "'publicName': 'Brais Moure',\n" +
                "'realUsername': 'MoureDev',\n" +
                "'tweetContent': 'Así funcionan todos los JOIN en SQL.\n\nLos dos mejores esquemas que he encontrado.',\n" +
                "'verifiedLogo': 'true'\n" +
                "},\n" +
                "{\n" +
                "'cantBookmarks': 21,\n" +
                "'cantLikes': 744,\n" +
                "'cantReplies': 33,\n" +
                "'cantRetweetsAndReposts': 48,\n" +
                "'cantViews': 33400,\n" +
                "'device': 'Android',\n" +
                "'hasMedia': 'true',\n" +
                "'hourAndDate': 1713102480,\n" +
                "'mediaCount': 3,\n" +
                "'photoUrl1': 'https://pbs.twimg.com/media/GLIUP5DWwAA_Q_U?format=jpg&name=small',\n" +
                "'photoUrl2': 'https://pbs.twimg.com/media/GLIUP5DXQAATrPa?format=jpg&name=small',\n" +
                "'photoUrl3': 'https://pbs.twimg.com/media/GLIUP5GWsAARDJQ?format=jpg&name=small',\n" +
                "'photoUrl4': '',\n" +
                "'profilePhoto': 'https://pbs.twimg.com/profile_images/1770770254198640641/MW4GrVo8_normal.jpg',\n" +
                "'publicName': 'Brais Moure',\n" +
                "'realUsername': 'MoureDev',\n" +
                "'tweetContent': 'No tengo palabras para lo que ocurrió ayer en @nerdearla Chile \uD83C\uDDE8\uD83C\uDDF1\n\n- El auditorio donde estaba dando mi charla se llenó.\n\n- El otro auditorio también se completó para verla en la pantalla.\n\n- Toda una fila de personas esperaban fuera del evento, no pudieron entrar por aforo y se quedaron a verla en una pantalla exterior.\n\nY a todo eso se le suman dos días de decenas de personas que se acercaron a saludarme y dedicarme bonitas palabras.\n\nNo sé cómo creerme esto y agradecer tanto apoyo. Vivo en Matrix.\n\nwhile(true) {\n    GRACIAS ♥️\n}',\n" +
                "'verifiedLogo': 'true'\n" +
                "},\n" +
                "{\n" +
                "'cantBookmarks': 1,\n" +
                "'cantLikes': 84,\n" +
                "'cantReplies': 2,\n" +
                "'cantRetweetsAndReposts': 9,\n" +
                "'cantViews': 7819,\n" +
                "'device': 'iOS',\n" +
                "'hasMedia': 'true',\n" +
                "'hourAndDate': 1713722460,\n" +
                "'mediaCount': 4,\n" +
                "'photoUrl1': 'https://pbs.twimg.com/media/GI0DNQiWoAAh6gy?format=jpg&name=large',\n" +
                "'photoUrl2': 'https://pbs.twimg.com/media/GI0DNQhW0AAXmMB?format=jpg&name=large',\n" +
                "'photoUrl3': 'https://pbs.twimg.com/media/GI0DNQkWgAABHLf?format=jpg&name=large',\n" +
                "'photoUrl4': 'https://pbs.twimg.com/media/GI0DNQjW4AAnm6M?format=jpg&name=large',\n" +
                "'profilePhoto': 'https://pbs.twimg.com/profile_images/1770770254198640641/MW4GrVo8_normal.jpg',\n" +
                "'publicName': 'Brais Moure',\n" +
                "'realUsername': 'MoureDev',\n" +
                "'tweetContent': '✅ Presentar mi evento favorito sobre tecnología y desarrollo junto a mi compi @Verownika.\n\nEsta semana he tenido el honor de presentar mi conferencia favorita. El @T3chFest \uD83E\uDD18\n\nHay tanto cariño dedicado en crear este festival sin ánimo de lucro que es imposible describirlo.\n\nCon nervios, pero intentando hacerlo lo mejor posible.\n\nVuelvo a casa después de reencontrarme con muchísimos compañeros, deseando que esté aquí ya la edición 2025.\n\nEnhorabuena por crear algo así, y millones de gracias por todo.',\n" +
                "'verifiedLogo': 'true'\n" +
                "},\n" +
                "{\n" +
                "'cantBookmarks': 613,\n" +
                "'cantLikes': 973,\n" +
                "'cantReplies': 4,\n" +
                "'cantRetweetsAndReposts': 264,\n" +
                "'cantViews': 32600,\n" +
                "'device': 'Android',\n" +
                "'hasMedia': 'true',\n" +
                "'hourAndDate': 1713808860,\n" +
                "'mediaCount': 1,\n" +
                "'photoUrl1': 'https://video.twimg.com/tweet_video/GLSgDlgXEAA5dOw.mp4',\n" +
                "'photoUrl2': '',\n" +
                "'photoUrl3': '',\n" +
                "'photoUrl4': '',\n" +
                "'profilePhoto': 'https://pbs.twimg.com/profile_images/1770770254198640641/MW4GrVo8_normal.jpg',\n" +
                "'publicName': 'Brais Moure',\n" +
                "'realUsername': 'MoureDev',\n" +
                "'tweetContent': 'Los 9 patrones arquitectónicos principales para el flujo de datos y comunicación.  \n\nOtra maravilla de ByteByteGo.\n\n→ Peer-to-Peer\nEl patrón Peer-to-Peer implica comunicación directa entre dos componentes sin la necesidad de un coordinador central.\n\n→ API Gateway\nUn API Gateway actúa como un único punto de entrada para todas las solicitudes de los clientes a los servicios de backend de una aplicación.\n\n→ Pub-Sub\nEl patrón Pub-Sub desacopla a los productores de mensajes (publicadores) de los consumidores de mensajes (suscriptores) a través de un intermediario de mensajes.\n\n→ Request-Response\nEste es uno de los patrones de integración más fundamentales, donde un cliente envía una solicitud a un servidor y espera una respuesta.\n\n→ Event Sourcing\nEvent Sourcing implica almacenar los cambios de estado de una aplicación como una secuencia de eventos.\n\n→ ETL\nETL es un patrón de integración de datos utilizado para recopilar datos de múltiples fuentes, transformarlos en un formato estructurado y cargarlos en una base de datos de destino.\n\n→ Batching\nBatching implica acumular datos durante un período o hasta alcanzar un cierto umbral antes de procesarlos como un solo grupo.\n\n→ Streaming Processing\nEl procesamiento de Streaming permite la ingestión, procesamiento y análisis continuos de flujos de datos en tiempo real.\n\n→ Orchestration\nLa orquestación implica que un coordinador central (un orquestador) gestiona las interacciones entre componentes o servicios distribuidos para lograr un flujo de trabajo o proceso de negocio.',\n" +
                "'verifiedLogo': 'true'\n" +
                "},\n" +
                "]")

        //Don't forget: This code (which loads the data into a Firestore Database) must ALWAYS go inside the "onCreate" method!
        val firebaseFirestore = FirebaseFirestore.getInstance()

        for (i in 0 until jsonData.length()-1)
        {
            val aux = jsonData.get(i) as JSONObject
            val cal = Calendar.getInstance()
            var tweetData = TweetData()

            tweetData.cantBookmarks = aux.getLong("cantBookmarks")
            tweetData.cantLikes = aux.getLong("cantLikes")
            tweetData.cantReplies = aux.getLong("cantReplies")
            tweetData.cantRetweetsAndReposts = aux.getLong("cantRetweetsAndReposts")
            tweetData.cantViews = aux.getLong("cantViews")
            tweetData.device = aux.getString("device")
            tweetData.hasMedia = aux.getBoolean("hasMedia")
            tweetData.mediaCount = aux.getInt("mediaCount")
            tweetData.photoUrl1 = aux.getString("photoUrl1")
            tweetData.photoUrl2 = aux.getString("photoUrl2")
            tweetData.photoUrl3 = aux.getString("photoUrl3")
            tweetData.photoUrl4 = aux.getString("photoUrl4")
            tweetData.profilePhoto = aux.getString("profilePhoto")
            tweetData.publicName = aux.getString("publicName")
            tweetData.realUsername = aux.getString("realUsername")
            tweetData.tweetContent = aux.getString("tweetContent")
            tweetData.verifiedLogo = aux.getBoolean("verifiedLogo")

            cal.timeInMillis = aux.getLong("hourAndDate") * 1000
            tweetData.hourAndDate = cal.time

            firebaseFirestore.collection("tweetsData").document().set(tweetData)
        }
    }

    //Method to indicate the bottom navigation menu and the navigation controller
    fun configNav() {
        NavigationUI.setupWithNavController(bnvMenu, Navigation.findNavController(this, R.id.fragmentContainer))
    }
}