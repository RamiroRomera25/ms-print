package rami.generic.config;

//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
/*
@Configuration
@Profile("firebase")
public class FirebaseConfig {

    public FirebaseConfig(@Value("file:.env/firebase_database_url.txt") Resource resource,
                          @Value("${firebase.config.path}") String firebaseConfigPath
                            ) throws IOException {

        String databaseURL;
        try (InputStream inputStream = resource.getInputStream()) {
            databaseURL = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8).trim();
        }
        FileInputStream serviceAccount =
                new FileInputStream(firebaseConfigPath);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(databaseURL)
                .build();

        FirebaseApp.initializeApp(options);

    }
}
*/
