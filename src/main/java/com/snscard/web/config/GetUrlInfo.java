package com.snscard.web.config;

import com.snscard.web.GetGithub.GetGithubInfo;
import com.snscard.web.GetNaver.GetNaverInfo;
import com.snscard.web.GetTistory.GetTistoryInfo;
import com.snscard.web.UrlApi.Url;
import com.snscard.web.utils.GetTitleDate;
import com.snscard.web.utils.Result_Url;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUrlInfo {
    private GetTitleDate info;

    public GetTitleDate getUrl(String url,Url gnt) throws Exception {
        if(url ==null || url.isEmpty()){
            return new GetTitleDate("","0");
        }
        if(gnt instanceof GetGithubInfo)
        {
            try{
                info=new GetGithubInfo().getInfo(url);
            }catch(Exception e){
                return new GetTitleDate("","0");
            }
        } else if (gnt instanceof GetNaverInfo)
        {
           info=new GetNaverInfo().getInfo(url);
        }else if (gnt instanceof GetTistoryInfo)
        {
            info=new GetTistoryInfo().getInfo(url);
        }
        else {
            return new GetTitleDate("","0");
        }
        return info;
    }
}