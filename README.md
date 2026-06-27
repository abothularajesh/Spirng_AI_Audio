# 🎙️ Spring AI Audio Models

A Spring Boot backend application that integrates **OpenAI's Audio Transcription API** to convert speech (audio files) into text. This project demonstrates how to use **Spring AI** with OpenAI's Whisper model for accurate, real-time speech-to-text transcription.

---

## 🚀 Tech Stack

| Layer        | Technology                        |
|--------------|-----------------------------------|
| Framework    | Spring Boot 3.x                   |
| AI Layer     | Spring AI                         |
| Audio Model  | OpenAI Whisper (`whisper-1`)      |
| Build Tool   | Maven                             |
| Language     | Java 21+                          |
| API Type     | REST (Backend Only)               |

---

## 📁 Project Structure

```
Spring-AI-Audio-Models/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/springaiaudio/
│       │       ├── SpringAiAudioApplication.java      # Main entry point
│       │       ├── controller/
│       │       │   └── AudioController.java           # REST controller for audio endpoints
│       │       └── service/
│       │           └── AudioTranscriptionService.java # OpenAI transcription logic
│       └── resources/
│           └── application.properties                 # API keys and config
├── pom.xml
└── README.md
```

---

## ⚙️ Configuration

Add the following to your `application.properties`:

```properties
# OpenAI API Key
spring.ai.openai.api-key=YOUR_OPENAI_API_KEY

# Audio Transcription Model
spring.ai.openai.audio.transcription.model=whisper-1
spring.ai.openai.audio.transcription.language=en
spring.ai.openai.audio.transcription.response-format=text
```

> ⚠️ **Never commit your API key to version control.** Use environment variables or a `.env` file instead.

---

## 📡 API Endpoints

### `POST /api/audio/transcribe`

Accepts an audio file and returns the transcribed text.

**Request:**
```
Content-Type: multipart/form-data
Body: file = <audio file (.mp3, .wav, .m4a, etc.)>
```

**Response:**
```json
{
  "transcription": "Hello, this is the transcribed text from your audio file."
}
```

---

## 🧠 How It Works

1. The client sends a `POST` request with an audio file to `/api/audio/transcribe`.
2. The `AudioController` receives the `MultipartFile` and passes it to `AudioTranscriptionService`.
3. The service uses **Spring AI's `OpenAiAudioTranscriptionModel`** to send the audio to OpenAI's Whisper API.
4. The transcribed text is returned as a plain string response.

---

## 🛠️ Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.8+
- An [OpenAI API Key](https://platform.openai.com/account/api-keys)

### Run the Application

```bash
# Clone the repository
git clone git@github.com:abothularajesh/Spirng_AI_Audio.git
cd Spirng_AI_Audio

# Add your API key to application.properties

# Build and run
mvn spring-boot:run
```

The server will start at `http://localhost:8080`.

---

## 🧪 Test with cURL

```bash
curl -X POST http://localhost:8080/api/audio/transcribe \
  -F "file=@/path/to/your/audio.mp3"
```

---

## 📦 Key Dependencies (`pom.xml`)

```xml
<!-- Spring AI OpenAI Starter -->
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-openai-spring-boot-starter</artifactId>
</dependency>

<!-- Spring Web -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

---

## 🔒 Supported Audio Formats

OpenAI Whisper supports the following formats:

`mp3` · `mp4` · `mpeg` · `mpga` · `m4a` · `wav` · `webm`

---

## 📌 Notes

- This is a **backend-only** project. No frontend UI is included.
- The transcription is powered by OpenAI's **Whisper-1** model via Spring AI abstraction.
- Spring AI's `OpenAiAudioTranscriptionModel` handles authentication, request building, and response parsing automatically.

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).

---

## 👤 Author

**Rajesh Abothula**  
GitHub: [@abothularajesh](https://github.com/abothularajesh)
