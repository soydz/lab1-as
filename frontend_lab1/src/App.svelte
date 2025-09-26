<script>
  import '@picocss/pico'
  import Router from 'svelte-spa-router';

  import ConstumerCreate from './routes/ConstumerCreate.svelte';
  import TransactionTransferMoney from './routes/TransactionTransferMoney.svelte';
  import TransactionHistory from './routes/TransactionHistory.svelte';
  import NotFound from './routes/NotFound.svelte';
  import Nav from './components/Nav.svelte';
  import Footer from './components/Footer.svelte';
  import ConstumerSeeAll from './routes/ConstumerSeeAll.svelte';
  import TransactionSearch from './routes/TransactionSearch.svelte';


  let routes = {
        "/": ConstumerCreate,
        "/customer/create": ConstumerCreate,
        "/customer/see_all": ConstumerSeeAll,
        "/transaction/transfer": TransactionTransferMoney,
        "/transaction/search": TransactionSearch,
        "/transaction/history": TransactionHistory,


        "*": NotFound
    }

  if(!window.location.hash) {
    window.location.hash = "#/"
  }    
</script>


<div class="page">
  <header>
    <Nav />
  </header>

  <main>
    <Router {routes} />
  </main>

  <footer>
    <Footer />
  </footer>
</div>

<style>
.page {
    display: flex;
    flex-direction: column;
    min-height: 97vh;
  }

  header {
    display: flex;
    justify-content: end;
    margin: 0 5rem;
  }

  main {
    flex: 1;
    max-width: 1280px;
    width: 100%;
    margin: 0 auto;
    padding: 2rem;
  }
</style>
