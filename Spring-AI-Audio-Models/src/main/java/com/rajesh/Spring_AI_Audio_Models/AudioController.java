package com.rajesh.Spring_AI_Audio_Models;

import com.openai.models.audio.AudioResponseFormat;
import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.tts.TextToSpeechPrompt;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api")
public class AudioController {

    private OpenAiAudioTranscriptionModel audioModel;

    private OpenAiAudioSpeechModel audioSpeechModel;

    public AudioController(OpenAiAudioTranscriptionModel audioModel, OpenAiAudioSpeechModel audioSpeechModel){
        this.audioModel=audioModel;
        this.audioSpeechModel=audioSpeechModel;S
    }

    @PostMapping("stt")
    public String speechToText(@RequestParam MultipartFile file){

        OpenAiAudioTranscriptionOptions options=OpenAiAudioTranscriptionOptions.builder()
                //While speech to text change the languages also
//                .language("hi")
                .responseFormat(AudioResponseFormat.SRT) //Time stamp
                .build();
        //This is for accepting the file as prompt
        //Older days the audio file directly accepting the file but some changes happen.
        AudioTranscriptionPrompt prompt=new AudioTranscriptionPrompt(file.getResource(),options);
        //spring-ai current version
        //System.out.println(OpenAiAudioTranscriptionOptions.class.getPackage().getImplementationVersion());

        //This is the current version of the speech to text audio model.
        return audioModel
                .call(prompt)
                .getResult()
                .getOutput();
    }
    @PostMapping("tts")
    public byte[] textToSpeech(@RequestParam String text){
        //Set up for the textToSpeech AudioSpeech Models
        OpenAiAudioSpeechOptions options= OpenAiAudioSpeechOptions.builder()
                .speed(1.5)    //set speed if needed
                .voice(OpenAiAudioSpeechOptions.Voice.ASH) //Voice changing
                .build();
        TextToSpeechPrompt prompt=new TextToSpeechPrompt(text,options);
        return audioSpeechModel.call(text);
    }
}
