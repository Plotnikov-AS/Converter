<#import "parts/page.ftlh" as p>

<@p.page>
    <link rel="stylesheet" type="text/css" href="css/Style.css">
    <table>
        <tr>
            <td>${from}</td>
            <td>${to}</td>
        </tr>
        <tr>
            <td>${fromAmount}</td>
            <td>${convertRes}</td>
        </tr>
        <tr>
            <td>
                <button><a href="/main">Main page</a></button>
            </td>
        </tr>
    </table>

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