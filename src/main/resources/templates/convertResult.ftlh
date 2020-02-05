<#import "parts/page.ftlh" as p>

<@p.page>
    <link rel="stylesheet" type="text/css" href="css/ConvertResStyle.css">
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
        <div class="container-history">
        <table>
            <tr>
                <th>
                    Исходная валюта
                </th>
                <th>
                    Целевая валюта
                </th>
                <th>
                    Исходная сумма
                </th>
                <th>
                    Получаемая сумма
                </th>
                <th>
                    Дата
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
        </div>
    </#list>
</@p.page>