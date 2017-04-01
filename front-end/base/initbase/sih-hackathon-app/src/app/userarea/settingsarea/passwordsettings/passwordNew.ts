// export class newPass {
//   constructor(
//     // public id: number,
//     // public name: string,
//     // public power: string,
//     // public alterEgo?: string
//
//     public emailId : string,
//     public password : string
//   ) {  }
// }

export interface newPass{
    // name: string; // required, must be 5-8
    // contact : string;
    // email: string; // required, must be valid email format
    // password: string; // required, value must be equal to confirm password.
    // confirmPassword: string; // required, value must be equal to password.


  oldPass : string,
  newPass : string,
  newPassX : string
}
