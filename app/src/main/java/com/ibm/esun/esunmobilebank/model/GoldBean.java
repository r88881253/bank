package com.ibm.esun.esunmobilebank.model;

import java.io.Serializable;

public class GoldBean implements Serializable {

    private final static String BUY = "Buy";

    private final static String CURCD = "Curcd";

    private final static String CURRENCY = "Currency";

    private final static String CURRENCY_TEXT = "CurrencyText";

    private final static String DP_DIFF = "DPDiff";

    private final static String GOOD_NAME = "GoodName";

    private final static String GOOD_NO = "GoodName";

    private final static String INFO_DATE_TIME = "InfoDateTime";

    private final static String SELL = "Sell";

    private final static String SELL_T = "SellT";
}


/*

1.台幣存放款利率

{
   "UpdateTime":"2018/06/12",
   "depositeInterestRateList":{
      "DepositeRate":[
         {
            "fRate":"不計息",
            "mRate":"不計息",
            "title":"支票存款"
         },
         {
            "fRate":"-",
            "mRate":"0.010",
            "title":"活期存款"
         }
      ]
   },
   "loanInterestRateList":{
      "LoanRate":[
         {
            "Rate":"15.000",
            "title":"信用卡最高利率"
         },
         {
            "Rate":"14.880",
            "title":"信用卡次高利率"
         }
      ]
   }
}






2.黃金存摺總覽

{
   "GoldPrice":[
      {
         "Buy":"1200000.00",
         "Curcd":"00",
         "Currency":"NTD",
         "CurrencyText":"新台幣",
         "DPDiff":"6809.00",
         "GoodName":"黃金條塊1公斤",
         "GoodNo":"GB0010001000",
         "InfoDateTime":"2017-01-06T13:55:00",
         "Sell":"1221809.00",
         "SellT":"0.00"
      },
      {
         "Buy":"600000.00",
         "Curcd":"00",
         "Currency":"NTD",
         "CurrencyText":"新台幣",
         "DPDiff":"4011.00",
         "GoodName":"黃金條塊500克",
         "GoodNo":"GB0010002000",
         "InfoDateTime":"2017-01-06T13:55:00",
         "Sell":"611511.00",
         "SellT":"0.00"
      },
      {
         "Buy":"300000.00",
         "Curcd":"00",
         "Currency":"NTD",
         "CurrencyText":"新台幣",
         "DPDiff":"2309.00",
         "GoodName":"黃金條塊250克",
         "GoodNo":"GB0010003000",
         "InfoDateTime":"2017-01-06T13:55:00",
         "Sell":"306059.00",
         "SellT":"0.00"
      }
   ]
}





3.玉山e理財

{
   "CreditCardBusiness":[
      {
         "title":"信用卡申請",
         "phone":"02-xxx-xxxx"
      },
      {
         "title":"帳單查詢",
         "phone":"02-xxx-xxxx"
      },
      {
         "title":"開卡服務",
         "phone":"02-xxx-xxxx"
      },
      {
         "title":"信用卡掛失",
         "phone":"02-xxx-xxxx"
      },
      {
         "title":"自扣戶申請",
         "phone":"02-xxx-xxxx"
      }
   ],
   "DepositeInterestBusiness":[
      {
         "title":"存戶服務",
         "phone":"掛失服務"
      },
      {
         "title":"掛失服務",
         "phone":"02-xxx-xxxx"
      }
   ],
   "InvestmentBusiness":[
      {
         "title":"基金服務",
         "phone":"02-xxx-xxxx"
      },
      {
         "title":"理財服務",
         "phone":"02-xxx-xxxx"
      }
   ]
}
 */