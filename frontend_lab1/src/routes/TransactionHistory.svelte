<script>
    import { transactionService } from "../services/transactionService";
    import { httpMethod } from "../services/httpMethodEnum";

    let transactions = [];
    let error = null;
    let notFoundData = null;

    let accountNumber = null;

    async function handleSubmit() {
        try {
            transactions = await transactionService({
                method: httpMethod.GET,
                id:accountNumber
            });

            if (transactions.length == 0) {
                notFoundData = true;
            }

        } catch(e) {
            error = e.message;
        }
    };

</script>

<h1>Transaction history</h1>

<form on:submit|preventDefault={handleSubmit}>
    <fieldset role="group">
        <input type="number" name="account_number" bind:value={accountNumber} placeholder="Enter the account number to search" min="0" required/>
        <input type="submit" value="Search"/>
    </fieldset>
</form>


{#if error}
    <p class="error">{ error }</p>

{:else if notFoundData}
    <h4>There are no recorded transactions</h4>

{:else if transactions.length > 0}
    <table>
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Id</th>
                <th scope="col">Receiver Account</th>
                <th scope="col">Amount</th>
            </tr>
        </thead>
        <tbody>
            {#each transactions as t, i}
                <tr>
                    <th scope="row">{ i + 1 }</th>
                    <td>{ t.id }</td>
                    <td>{ t.receiverAccountNumber }</td>
                    <td>{ t.amount }</td>
                </tr>
            {/each}
            
        </tbody>
    </table>
{/if}

<style>
    table {
        margin-top: 1rem;
    }

    h4 {
        text-align: center;
    }

    .error {
        color: brown;
    }
</style>
