// This file can be replaced during build by using the `fileReplacements` array.
// `ng build ---prod` replaces `environment.dev.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
    apiUrl: 'http://localhost:4000/auto-park-service',
    authorizeOauthClientId: 'cloud-auto-park-service-password-grant-client',
    authorizeOauthClientSecret: 'cloud-auto-park-service-password-grant-client',
    apiClientId: 'cloud-auto-park-service',
    apiClientSecret: 'cloud-auto-park-service',
    oauthServiceUrl: 'http://localhost:4000/auth-service',
  }
;

/*
 * In development mode, to ignore zone related error stack frames such as
 * `zone.run`, `zoneDelegate.invokeTask` for easier debugging, you can
 * import the following file, but please comment it out in production mode
 * because it will have performance impact when throw error
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
