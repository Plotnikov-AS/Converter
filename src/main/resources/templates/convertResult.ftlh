<#import "parts/page.ftlh" as p>

<@p.page>
    <link rel="stylesheet" type="text/css" href="css/Style.css">
    <div class="container">
        <h1>Результат</h1>
    <article class="line">${from}</article>
    <article class="line">${to}</article>

    <article class="line">${fromAmount}</article>
    <article class="line">${convertRes}</article>
        <a href="/main">Main page</a>
    </div>



    <#list histories as history>
        <#if history?index == 5>
            <#break>
        </#if>
        <table>
            <tr>
                <th>
                    From
                </th>
                <th>
                    To
                </th>
                <th>
                    From amount
                </th>
                <th>
                    To amount
                </th>
                <th>
                    Date
                </th>
            </tr>
            <tr>
                <td>
                    ${history.fromCurrency}
                </td>
                <td>
                    ${history.toCurrency}
                </td>
                <td>
                    ${history.fromAmount}
                </td>
                <td>
                    ${history.toAmount}
                </td>
                <td>
                    ${history.date?date}
                </td>
            </tr>
        </table>
    </#list>
</@p.page>