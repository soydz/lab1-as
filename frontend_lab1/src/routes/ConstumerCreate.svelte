<script>
    import { customerService } from "../services/customerService";
    import { httpMethod } from "../services/httpMethodEnum";

    let customer = {
        firstName: "",
        lastName: "",
        accountNumber: null,
        balance: null,
    };

    let notification = null;
    let error = null;
    let exito = null;

    async function handleSubmit() {
        try {
            const saved = await customerService({
                method: httpMethod.POST,
                data: customer,
            });

            customer = {
                firstName: "",
                lastName: "",
                accountNumber: null,
                balance: null,
            };
            notification = true;
            exito = `cliente creado satisfactoriamente`
            
        } catch (e) {
            notification = true;
            error = e.message;
        } finally {
            setTimeout(() => {
                notification = null;
                exito = null;
                error = null;
                
            }, 4000);
        }
    }
</script>

<div>
    <h1>Register customer</h1>
    <form on:submit|preventDefault={handleSubmit}>
        <fieldset>
            <label>
                First Name
                <input
                    type="text"
                    name="first_name"
                    bind:value={customer.firstName}
                    placeholder="Sofia"
                    required
                />
            </label>
            <label>
                Last Name
                <input
                    type="text"
                    name="last_name"
                    bind:value={customer.lastName}
                    placeholder="Vergara"
                    required
                />
            </label>
        </fieldset>
        <fieldset class="grid">
            <label>
                Account Number
                <input
                    type="number"
                    name="account_number"
                    bind:value={customer.accountNumber}
                    placeholder="98475212154521"
                    min="0"
                    required
                />
            </label>
            <label>
                Balance
                <input
                    type="number"
                    name="balance"
                    bind:value={customer.balance}
                    placeholder="983200"
                    min="0"
                    required
                />
            </label>
        </fieldset>
        <input type="submit" value="Save" />
    </form>
</div>

{#if notification}
    <dialog open>
        <article>
            <header>
                <button
                    on:click={() => (notification = null)}
                    aria-label="close"
                    rel="prev"
                ></button>
                {#if error}
                    <p>Error</p>
                {:else if exito}
                    <p>Exito</p>
                {/if}
            </header>

            {#if error}
                <p>{error}</p>
            {:else if exito}
                <p>{exito}</p>
            {/if}
        </article>
    </dialog>
{/if}

<style>
    
</style>
