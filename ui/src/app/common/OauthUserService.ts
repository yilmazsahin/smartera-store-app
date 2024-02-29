/**
 * @author yilmazsahin
 * @since 2/24/2024
 */
import {Customer} from "./customer";


export default class OauthUserService {

  private static user_key = "oauth_user";
  static setUser = (customer: Customer): void => {
    localStorage.setItem(this.user_key, JSON.stringify(customer));
  }

  static getUser = (): Customer | undefined => {
    const localStorageUser = localStorage.getItem(this.user_key);
    if (localStorageUser)
      return JSON.parse(localStorageUser);
    return undefined;
  }

}
