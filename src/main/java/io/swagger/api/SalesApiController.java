package io.swagger.api;

import io.swagger.model.SalesResponse;
import io.swagger.repository.DiscountDatabase;
import io.swagger.repository.ProductDatabase;
import io.swagger.service.SalesService;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme.In;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.util.ArrayList;
import io.swagger.model.SalesRequest;
import io.swagger.model.SalesRequestProductItems;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-10-08T19:58:21.879000852Z[GMT]")
@RestController
public class SalesApiController implements SalesApi {

    private static final Logger log = LoggerFactory.getLogger(SalesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final SalesService salesService;

    @org.springframework.beans.factory.annotation.Autowired
    public SalesApiController(ObjectMapper objectMapper, HttpServletRequest request, SalesService salesService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.salesService = salesService;
    }

    public ResponseEntity<SalesResponse> submitSalesRequest(
            @Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody SalesRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {

            System.out.println("salesRequest = " + body);

            SalesResponse response = salesService.processSalesRequest(body);

            if (response != null) {
                return new ResponseEntity<SalesResponse>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<SalesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }

        return new ResponseEntity<SalesResponse>(HttpStatus.BAD_REQUEST);
    }

}
