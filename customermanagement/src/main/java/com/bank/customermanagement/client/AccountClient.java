package com.bank.customermanagement.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import com.bank.customermanagement.model.AddAccountRequest;
import com.bank.customermanagement.model.GeneralResponse;


@FeignClient(name = "accountmanagment" )
public interface AccountClient {
	 @PostMapping("api/v1/account")
	    public ResponseEntity<GeneralResponse>  addAccount( AddAccountRequest addAccountRequest) ;
	   
}
