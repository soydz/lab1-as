<script>
    import { onMount } from "svelte";
    import { customerService } from "../services/customerService";
    import { httpMethod } from "../services/httpMethodEnum";

    let customers = [];
    let error = null;
    let loading = true;

    onMount(async () => {
        try {
            customers = await customerService({
                method: httpMethod.GET,
                data: null
            });

        } catch(e) {
            error = e.message;

        } finally {
            loading = false;
        }
    });

</script>

<h3>Customers list</h3>

{#if loading}
    <p>loading...</p>


{:else if error}
    <p class="error">{error}</p>

{:else}
<table class="striped">
    <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Id</th>
            <th scope="col">Account Number</th>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Balance</th>
        </tr>
    </thead>
    
    <tbody>
        {#each customers as c, i}
            <tr>
                <th scope="row">{ i + 1 }</th>
                <td>{ c.id }</td>
                <td>{ c.accountNumber }</td>
                <td>{ c.firstName }</td>
                <td>{ c.lastName }</td>
                <td>{ c.balance }</td>
            </tr>
        {/each}
    </tbody>
</table>
{/if}


<style>
    .error {
        color: brown;
    }
</style>