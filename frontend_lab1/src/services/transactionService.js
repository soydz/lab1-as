import { httpMethod } from "./httpMethodEnum";

const BASE_URL = import.meta.env.VITE_API_URL;
const TRANSACTION_URL = import.meta.env.VITE_TRANSACTION_URL;

export async function transactionService({method, data=null, id=null}) {

    let url = BASE_URL + TRANSACTION_URL;

    const options = {
        method,
        headers: { "Content-Type": "application/json" }
    }

    if (method == httpMethod.POST || method == httpMethod.PUT || method == httpMethod.PATCH ) {
        options.body = JSON.stringify(data);
    }

    if (id) {
        url += `/${id}`
    }

    const res = await fetch( url, options);


    if (!res.ok) {
        const errorBody = await res.text().catch(() => {});
        const message = errorBody || `Error HTTP ${res.status}`;
        throw new Error(message);
    }


    return res.json();
}