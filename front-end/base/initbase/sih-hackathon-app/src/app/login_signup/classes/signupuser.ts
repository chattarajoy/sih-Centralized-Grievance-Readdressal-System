export interface signUp{
    name: string; // required, must be 5-8
    contact : string;
    email: string; // required, must be valid email format
    password: string; // required, value must be equal to confirm password.
    confirmPassword: string; // required, value must be equal to password.
}
