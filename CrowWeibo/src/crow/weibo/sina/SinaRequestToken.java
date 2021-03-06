/*Copyright (C) 2012 Crow Hou (crow_hou@126.com)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/
package crow.weibo.sina;

import crow.weibo.RequestToken;
import crow.weibo.WeiboException;
import crow.weibo.util.WeiboUtil;

public class SinaRequestToken extends RequestToken {
	private SinaWeibo weibo;

	public SinaRequestToken(SinaWeibo weibo, String oauthCallback,
			String token, String tokenSecret) {
		super(oauthCallback, token, tokenSecret);
		this.weibo = weibo;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public SinaAccessToken getAccessToken(String oauthCallBack)
			throws WeiboException {
		String verifier = WeiboUtil.parseToken(oauthCallBack).getString(
				"oauth_verifier");
		return weibo.getAccessToken(this, verifier);
	}

	@Override
	public String getAuthenticationURL() {
		String url = "http://api.t.sina.com.cn/oauth/authenticate?oauth_token="
				+ getToken() + "&display=mobile";
		return url;
	}
}
