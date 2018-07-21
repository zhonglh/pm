package com.pm.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.domain.business.VoucherInterim;
import com.pm.util.constant.EnumDataType;


@Controller
@RequestMapping(value = "VoucherInterimAction4Invoice.do")
public class VoucherInterimAction4Invoice extends VoucherInterimAction{
	

	private static final String rel = "rel93";

	@Override
	protected void setType(VoucherInterim voucherInterim) {
		voucherInterim.setData_type(EnumDataType.invoice.getId());
	}

}
