// package com.ravid.clothes_marketplace.app.service;

// public class ApiClient {
//     public ApiClient(String[] authNames) {
//         this();
//         for (String authName : authNames) {
//             Interceptor auth = null;
//             if ("Bearer".equals(authName)) {
//                 auth = new BearerTokenAuth();
//             } else {
//                 throw new RuntimeException("auth name \"" + authName + "\" not found in available auth names");
//             }
    
//             addAuthorization(authName, auth);
//         }
//     }
    
//    /**
//     * Helper method to pre-set the oauth access token of the first oauth found in the apiAuthorizations (there should be only one)
//     *
//     * @param accessToken Access token
//     * @return ApiClient
//     */
//    public ApiClient setAccessToken(String accessToken) {
//        for (Interceptor apiAuthorization : apiAuthorizations.values()) {
//            if (apiAuthorization instanceof OAuth) {
//                OAuth oauth = (OAuth) apiAuthorization;
//                oauth.setAccessToken(accessToken);
//                return this;
//            } else if (apiAuthorization instanceof BearerTokenAuth) {
//                final BearerTokenAuth bearerTokenAuth = (BearerTokenAuth) apiAuthorization;
//                bearerTokenAuth.setAccessToken(accessToken);
//                return this;
//            }
//        }
//        return this;
//    }
// }