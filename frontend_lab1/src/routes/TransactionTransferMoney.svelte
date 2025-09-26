<script>

    import { httpMethod } from "../services/httpMethodEnum";
    import { transactionService } from "../services/transactionService";

    let transfer = {
        senderAccountNumber: null,
        receiverAccountNumber: null,
        amount: null,
    };

    let notification = null;
    let error = null;
    let exito = null;

    async function handleSubmit() {
        try {
            const saved = await transactionService({
                method: httpMethod.POST,
                data: transfer,
            });

            exito = `Successful transfer. $${transfer.amount} has been sent to account ${transfer.receiverAccountNumber}`;

            transfer = {
                senderAccountNumber: null,
                receiverAccountNumber: null,
                amount: null,
            };


        } catch (e) {
            error = e.message;
            
        } finally {
            notification = true;

            setTimeout(() => {
                error = null;
                exito = null;
                notification = null;
            }, 4000);
        }
    }
</script>

<h1>Transfer</h1>

<form on:submit|preventDefault={handleSubmit}>
    <fieldset>
        <label>
            Sender Account
            <input
                type="number"
                name="sender_account"
                bind:value={transfer.senderAccountNumber}
                placeholder="128478514654"
                required
            />
        </label>
        <label>
            ReceiverAccount
            <input
                type="number"
                name="receiver_account"
                bind:value={transfer.receiverAccountNumber}
                placeholder="387132518646"
                required
            />
        </label>
        <label>
            Amount
            <input
                type="number"
                name="amount"
                bind:value={transfer.amount}
                placeholder="985000"
                min="0"
                required
            />
        </label>
        <input type="submit" value="Transfer" />
    </fieldset>
</form>

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

<style></style>
