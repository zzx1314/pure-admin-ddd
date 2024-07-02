package com.pure.interceptor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.pure.interceptor.dto.LoggerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static java.time.format.DateTimeFormatter.ofPattern;

@Service
@Slf4j
public class TraceServiceImpl implements TraceService {

    ObjectMapper om;

    LoggerDto loggerDto = LoggerDto.getInstance();

    @PostConstruct
    public void objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        String dateTimepattern = "yyyy-MM-dd HH:mm:ss";
        String datePattern = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(dateTimepattern);
        mapper.setDateFormat(dateFormat);
        mapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDate.class,
                new LocalDateDeserializer(ofPattern(datePattern)));
        javaTimeModule.addSerializer(LocalDate.class,
                new LocalDateSerializer(ofPattern(datePattern)));
        javaTimeModule.addDeserializer(LocalDateTime.class,
                new LocalDateTimeDeserializer(ofPattern(dateTimepattern)));
        javaTimeModule.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(ofPattern(dateTimepattern)));
        mapper.registerModule(javaTimeModule);
        om = mapper;
    }

    /**
     * Register the body of the response
     *
     * @param body Object
     * @author zzx
     */
    @Override
    public void registerBody(Object body) {
        loggerDto.setParametersOut(body);
    }

    /**
     * Register the body of the request
     *
     * @param body Object
     * @author zzx
     */
    @Override
    public void registerBodyRequest(Object body) {
        Map<String, Object> map = new HashMap<>();
        map.put("body", body);
        loggerDto.setParametersIn(map);
    }

    /**
     * Register the initial time of the request and the parameters of the request
     *
     * @param request HttpServletRequest
     * @author zzx
     */
    @Override
    public void registerInitTime(HttpServletRequest request) {
        loggerDto.setTimeConsumeService(new Date().getTime());
        if (request.getParameterMap().size() > 0) {
            loggerDto.setParametersIn(formatParameters(request.getParameterMap()));
        }
        loggerDto.setParametersIn(formatParameters(request.getParameterMap()));
    }

    /**
     * Register the trace of the request
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @author zzx
     */
    @Override
    public void registerTrace(HttpServletRequest request, HttpServletResponse response) {
        String timeResponse = (new Date().getTime() - loggerDto.getTimeConsumeService()) + " ms";
        loggerDto.setIp(request.getRemoteAddr());
        loggerDto.setMethod(request.getMethod());
        loggerDto.setPath(request.getRequestURI());
        loggerDto.setCodeResponse(response.getStatus());
        loggerDto.setTimeResponseService(timeResponse);
        try {
            String message = om.writeValueAsString(loggerDto);
            log.info("LOG{}", message);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    /**
     * Format the parameters of the request to a Map type
     *
     * @param requestParameters Map<String, String[]>
     * @return Map<String, Object>
     * @author zzx
     */
    private Map<String, Object> formatParameters(Map<String, String[]> requestParameters) {
        Map<String, Object> parameters = new HashMap<>();
        Map<String, Object> parametersIn = new HashMap<>();
        for (Map.Entry<String, String[]> entry : requestParameters.entrySet()) {
            parameters.put(entry.getKey(), entry.getValue()[0]);
        }
        parametersIn.put("query", parameters);
        return parametersIn;
    }
}
