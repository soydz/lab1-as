import { httpMethod } from "./httpMethodEnum";

const BASE_URL = import.meta.env.VITE_API_URL;
const CUSTOMERS_URL = import.meta.env.VITE_CUSTOMERS_URL;

export async function customerService({method, data=null, id=null}) {

    const options = {
        method,
        headers: { "Content-Type": "application/json" }
    }

    if (method == httpMethod.POST || method == httpMethod.PUT || method == httpMethod.PATCH ) {
        options.body = JSON.stringify(data);
    }

    const res = await fetch( (BASE_URL + CUSTOMERS_URL), options);


    if (!res.ok) {
        throw new Error(`Error ${res.status}: ${res.statusText}`);
    }

    return res.json();
}