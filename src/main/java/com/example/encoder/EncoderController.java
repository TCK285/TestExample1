package com.example.encoder;

import com.example.commonModel.ApiResponse;
import com.example.commonModel.ControllerPath;
import com.example.encoder.dto.EncoderDto;
import com.example.encoder.services.EncoderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class EncoderController {

    @Autowired
    private EncoderExample encoderExample;

    @PostMapping (ControllerPath.ENCODE)
    public ResponseEntity<ApiResponse> encode(@RequestBody EncoderDto encoderDto){
        ApiResponse apiResponse = encoderExample.encode(encoderDto);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PostMapping(ControllerPath.DECODE)
    public ResponseEntity<ApiResponse> decode(@RequestBody EncoderDto encoderDto){
        ApiResponse apiResponse = encoderExample.decode(encoderDto);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

}
