package com.example.encoder.services;

import com.example.commonModel.ApiResponse;
import com.example.commonModel.Messages;
import com.example.encoder.dto.EncoderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class EncoderExample {

    @Autowired
    private EncoderService encoderService;

    @Autowired
    private DecoderService decoderService;

    public ApiResponse encode(EncoderDto encoderDto){
        ApiResponse apiResponse = new ApiResponse();
        if (!encoderDto.getInput().isBlank()){
            String encodedText = encoderService.encode(encoderDto.getInput());
            encoderDto.setOutput(encodedText);

            apiResponse.setData(encoderDto);
            apiResponse.setStatus(HttpStatus.OK);
            apiResponse.setMessage(Messages.ENCODED_TEXT_IS+ encoderDto.getOutput());
            apiResponse.setStatusCode(200);
        }else{
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setStatusCode(400);
            apiResponse.setError(Messages.NULL_INPUT_TEXT);
        }
        return apiResponse;
    }

    public ApiResponse decode(EncoderDto encoderDto){
        ApiResponse apiResponse = new ApiResponse();
        if (!encoderDto.getInput().isBlank()){
            String decodedText = decoderService.decode(encoderDto.getInput());
            encoderDto.setOutput(decodedText);

            apiResponse.setData(encoderDto);
            apiResponse.setStatus(HttpStatus.OK);
            apiResponse.setStatusCode(200);
            apiResponse.setMessage(Messages.DECODED_TEXT_IS+ encoderDto.getOutput());
        }else{
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setStatusCode(400);
            apiResponse.setError(Messages.NULL_INPUT_TEXT);
        }
        return apiResponse;
    }

}
