<#import "parts/page.ftlh" as p>
<#import "parts/login.ftlh" as l>

<@p.page>
    Converter
    <form action="/convert" method="post">
        <table>
            <tr>
                <td>
                    <label>
                        From<br>
                        <select name="fromCode">
                            <#list curCode2Name as key, value>
                                <option value="${value}">${key}</option>
                            </#list>
                        </select>
                    </label>
                </td>
                <td>
                    <label>
                        To<br>
                        <select name="toCode">
                            <#list curCode2Name as key, value>
                                <option value="${value}">${key}</option>
                            </#list>
                        </select>
                    </label>
                </td>
            </tr>
            <tr>
                <td>
                    <label>
                        <input type="number" min=0 name="from">
                    </label>
                </td>
            </tr>
        </table>
        <button type="submit">Convert</button>
    </form>
<@l.logout/>
</@p.page>